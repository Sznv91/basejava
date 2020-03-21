package ru.topjava.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.topjava.basejava.exeption.ExistStorageException;
import ru.topjava.basejava.exeption.StorageException;

import java.sql.SQLException;

public class ExistExceptionUtil {
    private ExistExceptionUtil(){
    }

    public static StorageException convertException(SQLException e){
        if (e instanceof PSQLException){
            if(e.getSQLState().equals("23505")){
                return new ExistStorageException("no uuid");
            }
        }
        return new StorageException(e.getMessage(),"no uuid", e);
    }
}
