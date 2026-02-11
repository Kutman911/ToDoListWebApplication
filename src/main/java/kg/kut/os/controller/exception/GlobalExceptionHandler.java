package kg.kut.os.controller.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public String redirectToSpecificErrorPage(HttpServletResponse response) {
        switch (HttpStatus.valueOf(response.getStatus())) {
            case NOT_FOUND:
                return "redirect:/error/404";
            case FORBIDDEN:
                return "redirect:/error/403";
            default:
                return "redirect:/error/500";
        }
    }

    @RequestMapping("/error/500")
    public String getCommonErrorPage() {
        return "public/error/common-error-page";
    }

    @RequestMapping("/error/404")
    public String getNotFoundErrorPage() {
        return "public/error/not-found-error-page";
    }

    @RequestMapping("/error/403")
    public String getForbiddenErrorPage() {
        return "public/error/forbidden-error-page";
    }


    @ExceptionHandler(Throwable.class)
    public String handleNotFound(Throwable throwable) {
        return "redirect:/error/500";
    }
}
