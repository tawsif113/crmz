package com.wez.crm.util.constant;

public class RouteConstant {

  private RouteConstant() {
  }

  //PUBLIC resource paths
  public static final String AUTH_BASE_PATH = "/api/auth";
  public static final String SWAGGER_BASE_PATH = "/swagger";
  public static final String SWAGGER_V3_API_DOCS_PATH = "/v3/api-docs";
  public static final String SWAGGER_WEBJARS_PATH = "/webjars";
  public static final String SWAGGER_UI_PATH = "/swagger-ui";

  private static final String BASE_PATH = "/api";
  public static final String PATH_ID = "/{id}";


  //Roles & Permissions Section
  public static final String ROLE_PERMISSION_BASE_PATH = BASE_PATH + "/role-permissions";
  public static final String ROLE_BASE_PATH = "/roles";
  public static final String PERMISSION_BASE_PATH = "/permissions";
  public static final String GET_ROLE_BY_ID = ROLE_BASE_PATH + "/{id}";

  //CRM User Section
  public static final String CRM_USER_BASE_PATH = BASE_PATH + "/users";

}