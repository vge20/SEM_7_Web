package com.baeldung.controller;

import com.baeldung.DI.AppConfig;
import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors")
public class DoctorController extends HttpServlet {

    private AppConfig appConfig;

    private IDoctorService doctorService;

    public DoctorController() {
        try {
            appConfig = new AppConfig();
            doctorService = appConfig.getDoctorServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Doctor> doctorsList = doctorService.getDoctorsList();
        for (int i = 0; i < doctorsList.size(); i++) {
            System.out.println(doctorsList.get(i).getFirstName() + " " + doctorsList.get(i).getLastName());
        }
    }
}
