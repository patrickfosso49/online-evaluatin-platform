package com.projet.classwork.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

public class PostgreSqlArrayUserType implements UserType {

    @Override
    public int getSqlType() {
        return SqlTypes.ARRAY;
    }

    @Override
    public java.lang.Class returnedClass() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnedClass'");
    }

    @Override
    public boolean equals(Object x, Object y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    @Override
    public int hashCode(Object x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hashCode'");
    }

    @Override
    public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
            throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nullSafeGet'");
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nullSafeSet'");
    }

    @Override
    public Object deepCopy(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deepCopy'");
    }

    @Override
    public boolean isMutable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMutable'");
    }

    @Override
    public Serializable disassemble(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disassemble'");
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assemble'");
    }

    @Override
    public Object replace(Object detached, Object managed, Object owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }
    
}
