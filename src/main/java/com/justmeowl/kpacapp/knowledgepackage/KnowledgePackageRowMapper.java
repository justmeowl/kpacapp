package com.justmeowl.kpacapp.knowledgepackage;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KnowledgePackageRowMapper implements RowMapper<KnowledgePackage> {
    @Override
    public KnowledgePackage mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new KnowledgePackage(
                rs.getLong("Knowledge_package_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("creation_date").toLocalDate()
        );
    }

}
