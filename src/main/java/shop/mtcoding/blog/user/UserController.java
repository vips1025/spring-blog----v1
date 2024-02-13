package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.config.security.MyLoginUser;
import shop.mtcoding.blog.board.Board;


@RequiredArgsConstructor // final이 붙은 애들에 대한 생성자를 만들어줌
@Controller
public class UserController {

    // 자바는 final 변수는 반드시 초기화가 되어야함.
    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/user/{id}/update")
    public String update(@PathVariable int id, UserRequest.updateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { //401
            return "redirect:/loginForm";
        }

        User user = userRepository.findById(id);
        if (user.getId() != sessionUser.getId()) {
            return "error/403";
        }

        userRepository.update(requestDTO, id);
        session.setAttribute("sessionUser", user);
        System.out.println(requestDTO);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);
        String rawPassword = requestDTO.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);

        requestDTO.setPassword(encPassword);

        userRepository.save(requestDTO); // 모델에 위임하기
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(HttpServletRequest request, @AuthenticationPrincipal MyLoginUser myLoginUser) {
        User user = userRepository.findByUsername(myLoginUser.getUsername());
        request.setAttribute("user", user);
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
