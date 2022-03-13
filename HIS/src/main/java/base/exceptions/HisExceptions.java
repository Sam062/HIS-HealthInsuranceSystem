package base.exceptions;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import base.model.AccountModel;

@ControllerAdvice
public class HisExceptions {

	@ExceptionHandler(value = TransactionSystemException.class)
	public String ValidationExceptionHandler(Model model) {
		model.addAttribute("errMsg", "SERVER VALIDATION FAILED");
		return "Error";
	}
	@ExceptionHandler(value = RuntimeException.class)
	public String runtimeExceptionHandler(RuntimeException exp,Model model) {
		model.addAttribute("msg", exp.getMessage());
		model.addAttribute("accountModel", new AccountModel());
		return "loginPage";
	}

	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Model model) {
		model.addAttribute("errMsg", "SOMETHING WENT WRONG");
		return "Error";
	}

}
