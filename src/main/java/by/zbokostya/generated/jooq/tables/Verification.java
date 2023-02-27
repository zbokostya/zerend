/*
 * This file is generated by jOOQ.
 */
package by.zbokostya.generated.jooq.tables;


import by.zbokostya.generated.jooq.Indexes;
import by.zbokostya.generated.jooq.Keys;
import by.zbokostya.generated.jooq.Public;
import by.zbokostya.generated.jooq.tables.records.VerificationRecord;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Verification extends TableImpl<VerificationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.verification</code>
     */
    public static final Verification VERIFICATION = new Verification();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VerificationRecord> getRecordType() {
        return VerificationRecord.class;
    }

    /**
     * The column <code>public.verification.id</code>.
     */
    public final TableField<VerificationRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.verification.token</code>.
     */
    public final TableField<VerificationRecord, String> TOKEN = createField(DSL.name("token"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.verification.user</code>.
     */
    public final TableField<VerificationRecord, UUID> USER = createField(DSL.name("user"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.verification.expire_time</code>.
     */
    public final TableField<VerificationRecord, Instant> EXPIRE_TIME = createField(DSL.name("expire_time"), SQLDataType.INSTANT, this, "");

    private Verification(Name alias, Table<VerificationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Verification(Name alias, Table<VerificationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.verification</code> table reference
     */
    public Verification(String alias) {
        this(DSL.name(alias), VERIFICATION);
    }

    /**
     * Create an aliased <code>public.verification</code> table reference
     */
    public Verification(Name alias) {
        this(alias, VERIFICATION);
    }

    /**
     * Create a <code>public.verification</code> table reference
     */
    public Verification() {
        this(DSL.name("verification"), null);
    }

    public <O extends Record> Verification(Table<O> child, ForeignKey<O, VerificationRecord> key) {
        super(child, key, VERIFICATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.VERIFICATION_ID_UINDEX);
    }

    @Override
    public UniqueKey<VerificationRecord> getPrimaryKey() {
        return Keys.VERIFICATION_PK;
    }

    @Override
    public List<ForeignKey<VerificationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VERIFICATION__VERIFICATION_USER_ID_FK);
    }

    private transient User _user;

    /**
     * Get the implicit join path to the <code>public.user</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.VERIFICATION__VERIFICATION_USER_ID_FK);

        return _user;
    }

    @Override
    public Verification as(String alias) {
        return new Verification(DSL.name(alias), this);
    }

    @Override
    public Verification as(Name alias) {
        return new Verification(alias, this);
    }

    @Override
    public Verification as(Table<?> alias) {
        return new Verification(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Verification rename(String name) {
        return new Verification(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Verification rename(Name name) {
        return new Verification(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Verification rename(Table<?> name) {
        return new Verification(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, UUID, Instant> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super UUID, ? super String, ? super UUID, ? super Instant, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super UUID, ? super String, ? super UUID, ? super Instant, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}