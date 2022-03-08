package suptech.miage.tp6.sec;

import com.google.common.collect.Sets;

import java.util.Set;

import static suptech.miage.tp6.sec.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(TASK_READ,TASK_WRITE,TASK_DELETE)),
    MANAGER(Sets.newHashSet(TASK_READ,TASK_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
