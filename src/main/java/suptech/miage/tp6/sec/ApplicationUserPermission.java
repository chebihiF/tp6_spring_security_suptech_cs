package suptech.miage.tp6.sec;

public enum ApplicationUserPermission {

    TASK_READ("task:read"),
    TASK_WRITE("task:write"),
    TASK_DELETE("task:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
