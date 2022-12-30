package org.helei.retinalsegmentation.controller;


import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.dto.UserDTO;
import org.helei.retinalsegmentation.dto.UserLoginDTO;
import org.helei.retinalsegmentation.entity.User;
import org.helei.retinalsegmentation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-12-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public Result registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }


    @GetMapping("/confirm/{id}")
    public Result confirm(@PathVariable("id") Long id) {
        return userService.registerConfirm(id);
    }

    @GetMapping("/validCode")
    public void getValidCode(String username, HttpServletResponse response){
        userService.getCaptchaImg(username, response);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getCheckCode());
    }

    @PostMapping("/noPasswordValid")
    public Result noPasswordValid() {
        return userService.noPasswordValid();
    }

    @PostMapping("/uploadSrcImage")
    public Result uploadSrcImage(MultipartFile file) {
        return userService.uploadSrcImage(file);
    }

    @PostMapping("/getUserSrcImageList")
    public Result getUserSrcImageList() {
        return userService.getSrcImageList();
    }

    @PostMapping("/downloadResImage")
    public void downloadResImage(String path, HttpServletResponse response) {
        userService.downloadImage(path, response);
    }
}
