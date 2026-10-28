package bootcamp.hibernate_practical.controller;

import bootcamp.hibernate_practical.dto.BookForm;
import bootcamp.hibernate_practical.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookPageController {
    private final BookService bookService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        addUserInfo(model, authentication);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/books")
    public String listBooks(Model model, Authentication authentication) {
        addUserInfo(model, authentication);
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("form", new BookForm(null, null, null, null));
        return "books";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute BookForm form) {
        bookService.createFromForm(form);
        return "redirect:/books";
    }

    private void addUserInfo(Model model, Authentication authentication) {
        boolean loggedIn = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        boolean isAdmin = loggedIn && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("username", loggedIn ? authentication.getName() : null);
        model.addAttribute("isAdmin", isAdmin);
    }
}
