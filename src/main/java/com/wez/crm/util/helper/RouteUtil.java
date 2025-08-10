package com.wez.crm.util.helper;
import com.wez.crm.util.constant.RouteConstant;
import java.util.List;

public class RouteUtil {
    private RouteUtil() {}

    public static boolean isPublicResource(String path) {
        return path.contains(RouteConstant.SWAGGER_BASE_PATH) ||
                path.contains(RouteConstant.SWAGGER_UI_PATH) ||
                path.contains(RouteConstant.SWAGGER_WEBJARS_PATH) ||
                path.contains(RouteConstant.SWAGGER_V3_API_DOCS_PATH) ||
                path.contains(RouteConstant.AUTH_BASE_PATH);
    }

    public static List<String> getPublicRoutes() {
        return List.of(
                RouteConstant.SWAGGER_BASE_PATH,
                RouteConstant.SWAGGER_UI_PATH,
                RouteConstant.SWAGGER_WEBJARS_PATH,
                RouteConstant.SWAGGER_V3_API_DOCS_PATH,
                RouteConstant.AUTH_BASE_PATH
        );
    }
}