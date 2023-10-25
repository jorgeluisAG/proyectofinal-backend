package taller.grado.proyectofinalbackend.service.errors;

import taller.grado.proyectofinalbackend.controller.errors.BadRequestAlertException;
import taller.grado.proyectofinalbackend.controller.errors.BadRequestException;
import taller.grado.proyectofinalbackend.controller.errors.NotFoundException;
import taller.grado.proyectofinalbackend.utils.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static taller.grado.proyectofinalbackend.service.errors.ErrorConstants.*;

@ControllerAdvice
public class ExceptionTranslator  implements ProblemHandling {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String MESSAGE_KEY = "message";
    private static final String PATH_KEY = "path";
    private static final String VIOLATIONS_KEY = "violations";

    /**
     * Post-process the Problem payload to add the message key for the front-end if needed
     */
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return entity;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }
        ProblemBuilder builder = Problem.builder()
                .withStatus(problem.getStatus())
                .withTitle(problem.getTitle())
                .with("errorKey", INTERNAL_ERROR)
                .with(PATH_KEY, request.getNativeRequest(HttpServletRequest.class).getRequestURI());

        if (problem instanceof ConstraintViolationProblem) {
            builder
                    .with(VIOLATIONS_KEY, ((ConstraintViolationProblem) problem).getViolations())
                    .with("errorKey", ERROR_VALIDATION)
                    .with(MESSAGE_KEY, "error.validation");
        } else {
            if (Objects.equals(problem.getDetail(), USER_NOT_FOUND)) {
                builder
                        .withCause(((DefaultProblem) problem).getCause())
                        .withDetail("User not found in the database")
                        .with("errorKey", USER_NOT_FOUND)
                        .withInstance(problem.getInstance());
            } else {
                if (Objects.equals(problem.getDetail(), USER_NOT_ACTIVATED)) {
                    builder
                            .withCause(((DefaultProblem) problem).getCause())
                            .withDetail("User not activated")
                            .with("errorKey", USER_NOT_ACTIVATED)
                            .withInstance(problem.getInstance());
                } else if (Objects.equals(problem.getDetail(), "Bad credentials")) {
                    builder
                            .withTitle(UNAUTHORIZED)
                            .withStatus(Status.UNAUTHORIZED)
                            .withCause(((DefaultProblem) problem).getCause())
                            .withDetail("Bad Credentials")
                            .with("errorKey", BAD_CREDENTIALS)
                            .with(MESSAGE_KEY, "error.http.401")
                            .withInstance(problem.getInstance());
                } else{
                    builder
                            .withCause(((DefaultProblem) problem).getCause())
                            .withDetail(problem.getDetail())
                            .with("errorKey", INTERNAL_ERROR)
                            .withInstance(problem.getInstance());
                }
            }
            problem.getParameters().forEach(builder::with);
            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                builder.with(MESSAGE_KEY, "error.http." + problem.getStatus().getStatusCode());
            }
        }
        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getCode()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with("errorKey", ERROR_VALIDATION)
                .with(MESSAGE_KEY, "error.validation")
                .with(FIELD_ERRORS_KEY, fieldErrors)
                .build();
        return create(ex, problem, request);
    }


    @ExceptionHandler
    public ResponseEntity<Problem> handleNoSuchElementException(UserError ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withStatus(Status.UNAUTHORIZED)
                .withDetail(ex.getTitle())
                .with("errorKey", USER_NOT_FOUND)
                .with(MESSAGE_KEY, "error.entity.not.found")
                .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleNoSuchElementException(NoSuchElementException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withStatus(Status.NOT_FOUND)
                .with("errorKey", ENTITY_NOT_FOUND)
                .with(MESSAGE_KEY, "error.entity.not.found")
                .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
        return create(ex, request, HeaderUtil.createFailureAlert(ex.getEntityName(), ex.getErrorKey(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestException(BadRequestException ex, NativeWebRequest request) {
        return create(ex, request, HeaderUtil.createFailureAlert("", ex.getErrorKey(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleNotFoundException(NotFoundException ex, NativeWebRequest request) {
        return create(ex, request, HeaderUtil.createFailureAlert("", ex.getErrorKey(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex, NativeWebRequest request) {
        Problem problem = Problem.builder()
                .withStatus(Status.CONFLICT)
                .with("errorKey", ERROR_CONCURRENCY_FAILURE)
                .with(MESSAGE_KEY, "error.concurrencyFailure")
                .build();
        return create(ex, problem, request);
    }
}
