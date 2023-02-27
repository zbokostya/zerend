/*
 * This file is generated by jOOQ.
 */
package by.zbokostya.generated.jooq;


import by.zbokostya.generated.jooq.tables.Ability;
import by.zbokostya.generated.jooq.tables.Apikey;
import by.zbokostya.generated.jooq.tables.Project;
import by.zbokostya.generated.jooq.tables.Role;
import by.zbokostya.generated.jooq.tables.User;
import by.zbokostya.generated.jooq.tables.UserRole;
import by.zbokostya.generated.jooq.tables.Verification;
import by.zbokostya.generated.jooq.tables.records.AbilityRecord;
import by.zbokostya.generated.jooq.tables.records.ApikeyRecord;
import by.zbokostya.generated.jooq.tables.records.ProjectRecord;
import by.zbokostya.generated.jooq.tables.records.RoleRecord;
import by.zbokostya.generated.jooq.tables.records.UserRecord;
import by.zbokostya.generated.jooq.tables.records.UserRoleRecord;
import by.zbokostya.generated.jooq.tables.records.VerificationRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AbilityRecord> ABILITY_PK = Internal.createUniqueKey(Ability.ABILITY, DSL.name("ability_pk"), new TableField[] { Ability.ABILITY.ID }, true);
    public static final UniqueKey<ApikeyRecord> APIKEY_PK = Internal.createUniqueKey(Apikey.APIKEY, DSL.name("apikey_pk"), new TableField[] { Apikey.APIKEY.ID }, true);
    public static final UniqueKey<ProjectRecord> PROJECT_PK = Internal.createUniqueKey(Project.PROJECT, DSL.name("project_pk"), new TableField[] { Project.PROJECT.ID }, true);
    public static final UniqueKey<RoleRecord> ROLE_PK = Internal.createUniqueKey(Role.ROLE, DSL.name("role_pk"), new TableField[] { Role.ROLE.NAME }, true);
    public static final UniqueKey<UserRecord> USER_PK = Internal.createUniqueKey(User.USER, DSL.name("user_pk"), new TableField[] { User.USER.ID }, true);
    public static final UniqueKey<VerificationRecord> VERIFICATION_PK = Internal.createUniqueKey(Verification.VERIFICATION, DSL.name("verification_pk"), new TableField[] { Verification.VERIFICATION.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AbilityRecord, ProjectRecord> ABILITY__ABILITY_PROJECT_ID_FK = Internal.createForeignKey(Ability.ABILITY, DSL.name("ability_project_id_fk"), new TableField[] { Ability.ABILITY.PROJECT }, Keys.PROJECT_PK, new TableField[] { Project.PROJECT.ID }, true);
    public static final ForeignKey<ApikeyRecord, ProjectRecord> APIKEY__APIKEY_PROJECT_ID_FK = Internal.createForeignKey(Apikey.APIKEY, DSL.name("apikey_project_id_fk"), new TableField[] { Apikey.APIKEY.PROJECT }, Keys.PROJECT_PK, new TableField[] { Project.PROJECT.ID }, true);
    public static final ForeignKey<ProjectRecord, UserRecord> PROJECT__PROJECT_USER_ID_FK = Internal.createForeignKey(Project.PROJECT, DSL.name("project_user_id_fk"), new TableField[] { Project.PROJECT.OWNER }, Keys.USER_PK, new TableField[] { User.USER.ID }, true);
    public static final ForeignKey<UserRoleRecord, RoleRecord> USER_ROLE__USER_ROLE_ROLE_NAME_FK = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_role_name_fk"), new TableField[] { UserRole.USER_ROLE.USER_ROLE_ }, Keys.ROLE_PK, new TableField[] { Role.ROLE.NAME }, true);
    public static final ForeignKey<UserRoleRecord, UserRecord> USER_ROLE__USER_ROLE_USER_ID_FK = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_user_id_fk"), new TableField[] { UserRole.USER_ROLE.USER_ID }, Keys.USER_PK, new TableField[] { User.USER.ID }, true);
    public static final ForeignKey<VerificationRecord, UserRecord> VERIFICATION__VERIFICATION_USER_ID_FK = Internal.createForeignKey(Verification.VERIFICATION, DSL.name("verification_user_id_fk"), new TableField[] { Verification.VERIFICATION.USER }, Keys.USER_PK, new TableField[] { User.USER.ID }, true);
}
