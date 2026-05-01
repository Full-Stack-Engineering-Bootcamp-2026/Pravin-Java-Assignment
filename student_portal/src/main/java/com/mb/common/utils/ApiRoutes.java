package com.mb.common.utils;

public final class ApiRoutes {

    private ApiRoutes() {
    }

    public static final String ADMIN = "/admin";
    public static final String STUDENTS = "/students";

    public static final class Student {

        private Student() {
        }

        public static final String REGISTER = "/register";
        public static final String LOOKUP = "/lookup";
        public static final String ME = "/me";
        public static final String RESULT = "/result";
        public static final String MERIT = "/merit";
    }

    public static final class Admin {

        private Admin() {
        }

        public static final String STUDENTS = "/students";
        public static final String STUDENT_BY_ID = "/students/{id}";
        public static final String ADD_RESULT = "/results/{studentId}";
        public static final String UPDATE_RESULT = "/results/{resultId}";
        public static final String REPORTS = "/reports";
        public static final String LOWER_PERFORMANCE = "/reports/low";
    }
}