package com.thangnv2882.jobfastserver.application.constants;

public class UrlConstant {
  public static class Account {
    private static final String PRE_FIX = "/accounts";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET_BY_ID = PRE_FIX + "/{idAccount}";
    public static final String GET_BY_EMAIL = PRE_FIX + "/email";
    public static final String ADD_ROLE = PRE_FIX + "/add-role";
    public static final String REMOVE_ROLE = PRE_FIX + "/remove-role";
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idAccount}";
    public static final String CHANGE_AVATAR = PRE_FIX + "/change-avatar";

    public Account() {
    }

  }

  public static class Auth {
    private static final String PRE_FIX = "/auth";
    public static final String SIGNUP = PRE_FIX + "/sign-up";
    public static final String VERIFY_SIGNUP = SIGNUP + "/verify";
    public static final String SIGNIN = PRE_FIX + "/sign-in";
    public static final String FORGOT_PASSWORD = PRE_FIX + "/forgot-password";
    public static final String VERIFY_RESET_PASSWORD = FORGOT_PASSWORD + "/verify";
    public static final String UPDATE_PASSWORD = PRE_FIX + "/update-password";

    private Auth() {
    }
  }

  public static class Category {
    private static final String PRE_FIX = "/categories";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET = PRE_FIX + "/{idCategory}";
    public static final String CREATE = PRE_FIX;
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idCategory}";

    private Category() {
    }

  }

  public static class CV {
    private static final String PRE_FIX = "/cvs";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET = PRE_FIX + "/{idCV}";
    public static final String GET_BY_ACCOUNT = PRE_FIX + "/account/{idAccount}";
    public static final String UPLOAD_CV = PRE_FIX + "/upload-cv";
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idCV}";

    private CV() {
    }

  }


  public static class Job {
    private static final String PRE_FIX = "/jobs";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET = PRE_FIX + "/{idJob}";
    public static final String GET_BY_DETAIL_TYPE = PRE_FIX + "/detail-type";
    public static final String CREATE = PRE_FIX;
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idJob}";

    private Job() {
    }

  }

  public static class JobDetail {
    private static final String PRE_FIX = "/job-details";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET = PRE_FIX + "/{idJobDetail}";
    public static final String GET_APPLIED_BY_JOB = PRE_FIX + "/job/{idJob}";
    public static final String APPLY_JOB = PRE_FIX + "/apply-job";
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idJobDetail}";


    private JobDetail() {
    }

  }

  public static class Notification {
    private static final String PRE_FIX = "/notifications";
    public static final String GET = PRE_FIX + "/{idNotification}";
    public static final String GET_BY_ACCOUNT = PRE_FIX + "/account/{idAccount}";
    public static final String CREATE = PRE_FIX;
    public static final String SEND = PRE_FIX + "/send";
    public static final String READ = PRE_FIX + "/{idNotification}";
    public static final String READ_ALL = PRE_FIX + "/read/{idAccount}";
    public static final String UPDATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idNotification}";

    private Notification() {
    }

  }

  public static class Role {
    private static final String PRE_FIX = "/roles";
    public static final String GET_ALL = PRE_FIX;
    public static final String GET = PRE_FIX + "/{roleName}";
    public static final String CREATE = PRE_FIX;
    public static final String DELETE = PRE_FIX + "/{idRole}";

    private Role() {
    }
  }
}
