package com.justmeowl.kpacapp.knowledgepackageset;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KnowledgePackageSetRowMapper implements RowMapper<KnowledgePackageSet> {
    @Override
    public KnowledgePackageSet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new KnowledgePackageSet(
                rs.getLong("knowledge_package_set_id"),
                rs.getString("title")
        );
    }
}
