package com.zhou.myweather;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhou.myweather.db.dto.CityPO;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CITY_PO".
*/
public class CityPODao extends AbstractDao<CityPO, Void> {

    public static final String TABLENAME = "CITY_PO";

    /**
     * Properties of entity CityPO.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Areaid = new Property(0, String.class, "areaid", false, "AREAID");
        public final static Property Nameed = new Property(1, String.class, "nameed", false, "NAMEED");
        public final static Property Namecn = new Property(2, String.class, "namecn", false, "NAMECN");
        public final static Property Districten = new Property(3, String.class, "districten", false, "DISTRICTEN");
        public final static Property Districtcn = new Property(4, String.class, "districtcn", false, "DISTRICTCN");
        public final static Property Proven = new Property(5, String.class, "proven", false, "PROVEN");
        public final static Property Provcn = new Property(6, String.class, "provcn", false, "PROVCN");
        public final static Property Nationen = new Property(7, String.class, "nationen", false, "NATIONEN");
        public final static Property Nationcn = new Property(8, String.class, "nationcn", false, "NATIONCN");
    }


    public CityPODao(DaoConfig config) {
        super(config);
    }
    
    public CityPODao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY_PO\" (" + //
                "\"AREAID\" TEXT," + // 0: areaid
                "\"NAMEED\" TEXT," + // 1: nameed
                "\"NAMECN\" TEXT," + // 2: namecn
                "\"DISTRICTEN\" TEXT," + // 3: districten
                "\"DISTRICTCN\" TEXT," + // 4: districtcn
                "\"PROVEN\" TEXT," + // 5: proven
                "\"PROVCN\" TEXT," + // 6: provcn
                "\"NATIONEN\" TEXT," + // 7: nationen
                "\"NATIONCN\" TEXT);"); // 8: nationcn
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY_PO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CityPO entity) {
        stmt.clearBindings();
 
        String areaid = entity.getAreaid();
        if (areaid != null) {
            stmt.bindString(1, areaid);
        }
 
        String nameed = entity.getNameed();
        if (nameed != null) {
            stmt.bindString(2, nameed);
        }
 
        String namecn = entity.getNamecn();
        if (namecn != null) {
            stmt.bindString(3, namecn);
        }
 
        String districten = entity.getDistricten();
        if (districten != null) {
            stmt.bindString(4, districten);
        }
 
        String districtcn = entity.getDistrictcn();
        if (districtcn != null) {
            stmt.bindString(5, districtcn);
        }
 
        String proven = entity.getProven();
        if (proven != null) {
            stmt.bindString(6, proven);
        }
 
        String provcn = entity.getProvcn();
        if (provcn != null) {
            stmt.bindString(7, provcn);
        }
 
        String nationen = entity.getNationen();
        if (nationen != null) {
            stmt.bindString(8, nationen);
        }
 
        String nationcn = entity.getNationcn();
        if (nationcn != null) {
            stmt.bindString(9, nationcn);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CityPO entity) {
        stmt.clearBindings();
 
        String areaid = entity.getAreaid();
        if (areaid != null) {
            stmt.bindString(1, areaid);
        }
 
        String nameed = entity.getNameed();
        if (nameed != null) {
            stmt.bindString(2, nameed);
        }
 
        String namecn = entity.getNamecn();
        if (namecn != null) {
            stmt.bindString(3, namecn);
        }
 
        String districten = entity.getDistricten();
        if (districten != null) {
            stmt.bindString(4, districten);
        }
 
        String districtcn = entity.getDistrictcn();
        if (districtcn != null) {
            stmt.bindString(5, districtcn);
        }
 
        String proven = entity.getProven();
        if (proven != null) {
            stmt.bindString(6, proven);
        }
 
        String provcn = entity.getProvcn();
        if (provcn != null) {
            stmt.bindString(7, provcn);
        }
 
        String nationen = entity.getNationen();
        if (nationen != null) {
            stmt.bindString(8, nationen);
        }
 
        String nationcn = entity.getNationcn();
        if (nationcn != null) {
            stmt.bindString(9, nationcn);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public CityPO readEntity(Cursor cursor, int offset) {
        CityPO entity = new CityPO( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // areaid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nameed
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // namecn
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // districten
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // districtcn
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // proven
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // provcn
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // nationen
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // nationcn
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CityPO entity, int offset) {
        entity.setAreaid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNameed(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNamecn(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDistricten(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDistrictcn(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProven(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProvcn(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNationen(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNationcn(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(CityPO entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(CityPO entity) {
        return null;
    }

    @Override
    public boolean hasKey(CityPO entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}