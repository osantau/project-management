package oct.soft.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import oct.soft.pma.entities.UserAccount;
import oct.soft.pma.repositories.UserAccountRepo;

@Controller
public class SecurityController {

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	private final UserAccountRepo accountRepo;
	public SecurityController(UserAccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";

	}

	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRepo.save(user);
		return "redirect:/";
	}
}
