package com.revature.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "filecontroller", urlPatterns = {"/fileController"})
@MultipartConfig
public class FileController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String uniqueName = request.getParameter("uniqueName");
    Path uploadPath = Paths.get(Utils.getValueFromAppProperties("user_file_location"), uniqueName);
    Files.copy(uploadPath, response.getOutputStream());
    response.setContentType("image/jpeg");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

}
