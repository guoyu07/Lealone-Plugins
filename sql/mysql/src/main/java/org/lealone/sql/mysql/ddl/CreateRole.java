/*
 * Copyright 2004-2013 H2 Group. Multiple-Licensed under the H2 License,
 * Version 1.0, and under the Eclipse Public License, Version 1.0
 * (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.lealone.sql.mysql.ddl;

import org.lealone.api.ErrorCode;
import org.lealone.common.exceptions.DbException;
import org.lealone.db.Database;
import org.lealone.db.LealoneDatabase;
import org.lealone.db.ServerSession;
import org.lealone.db.auth.Auth;
import org.lealone.db.auth.Role;
import org.lealone.sql.SQLStatement;

/**
 * This class represents the statement
 * CREATE ROLE
 */
public class CreateRole extends DefineStatement {

    private String roleName;
    private boolean ifNotExists;

    public CreateRole(ServerSession session) {
        super(session);
    }

    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }

    public void setRoleName(String name) {
        this.roleName = name;
    }

    @Override
    public int update() {
        session.getUser().checkAdmin();
        session.commit(true);
        Database db = LealoneDatabase.getInstance();
        if (Auth.findUser(roleName) != null) {
            throw DbException.get(ErrorCode.USER_ALREADY_EXISTS_1, roleName);
        }
        if (Auth.findRole(roleName) != null) {
            if (ifNotExists) {
                return 0;
            }
            throw DbException.get(ErrorCode.ROLE_ALREADY_EXISTS_1, roleName);
        }
        int id = getObjectId(db);
        Role role = new Role(db, id, roleName, false);
        db.addDatabaseObject(session, role);
        return 0;
    }

    @Override
    public int getType() {
        return SQLStatement.CREATE_ROLE;
    }

}
