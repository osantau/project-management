package oct.soft.pma.controllers;

import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (Arrays.asList(HttpStatus.NOT_FOUND.value(), HttpStatus.FORBIDDEN.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value()).contains(statusCode)) {
				return "errorpages/error-" + statusCode;
			}
		}

		return "errorpages/error";
	}

	public String getErrorPath() {
		return "/error";
	}
}
