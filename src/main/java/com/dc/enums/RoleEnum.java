package com.dc.enums;

public enum RoleEnum {
    ADMIN(1L),
    LAB_SUPER_VISOR(2L),
    LAB_TECHNICIAN(3L);
    
    private final Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public static boolean isValid(Long id){
        for (RoleEnum role : RoleEnum.values()){
            if(role.getId().equals(id))
                return true;
        }
        return false;
    }

    public static String getRole(Long id){
        for (RoleEnum role : RoleEnum.values()){
            if(role.getId().equals(id))
                return role.name();
        }
        return null;
    }
}
