package com.app.MainVault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping
public class UsersController {
    private final UserRepository repository;

    public UsersController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/adduser")
    public User addUserUName(@RequestBody User username) {
        return this.repository.save(username);
    }

    public User addUserPassword(@RequestBody User password) {
        return this.repository.save(password);
    }

    @GetMapping("/login")
    public Object getCredentials(@RequestParam String username, @RequestParam String password,
                                 HttpServletResponse response) throws Exception {

        User creds = this.repository.findByUsername(username);
        int id = creds.getId();
        String str = creds.toString();
        String uid = Integer.toString(id);

        if (str.equals(username + password)) {
            response.addCookie(new Cookie("UserId", uid));
            return new ResponseEntity<String>("Logged In and Cookie Set!", HttpStatus.OK);
            //response.addCookie(cookie);
            //return id;
        } else {
            throw new Exception("Login Failed. Invalid Credentials");
        }
    }
}
