package com.cecurs.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Byte value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Byte value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Byte value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Byte value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Byte value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Byte> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Byte> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Byte value1, Byte value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Byte value1, Byte value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(int value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(int value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(int value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(int value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(int value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(int value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Integer> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Integer> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(int value1, int value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(int value1, int value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andHashIsNull() {
            addCriterion("hash is null");
            return (Criteria) this;
        }

        public Criteria andHashIsNotNull() {
            addCriterion("hash is not null");
            return (Criteria) this;
        }

        public Criteria andHashEqualTo(String value) {
            addCriterion("hash =", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotEqualTo(String value) {
            addCriterion("hash <>", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThan(String value) {
            addCriterion("hash >", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashGreaterThanOrEqualTo(String value) {
            addCriterion("hash >=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThan(String value) {
            addCriterion("hash <", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLessThanOrEqualTo(String value) {
            addCriterion("hash <=", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashLike(String value) {
            addCriterion("hash like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotLike(String value) {
            addCriterion("hash not like", value, "hash");
            return (Criteria) this;
        }

        public Criteria andHashIn(List<String> values) {
            addCriterion("hash in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotIn(List<String> values) {
            addCriterion("hash not in", values, "hash");
            return (Criteria) this;
        }

        public Criteria andHashBetween(String value1, String value2) {
            addCriterion("hash between", value1, value2, "hash");
            return (Criteria) this;
        }

        public Criteria andHashNotBetween(String value1, String value2) {
            addCriterion("hash not between", value1, value2, "hash");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(int value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(int value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(int value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(int value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(int value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(int value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(int value1, int value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(int value1, int value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(int value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(int value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(int value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(int value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(int value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(int value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(int value1, int value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(int value1, int value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisIsNull() {
            addCriterion("is_analysis is null");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisIsNotNull() {
            addCriterion("is_analysis is not null");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisEqualTo(int value) {
            addCriterion("is_analysis =", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisNotEqualTo(int value) {
            addCriterion("is_analysis <>", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisGreaterThan(int value) {
            addCriterion("is_analysis >", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisGreaterThanOrEqualTo(int value) {
            addCriterion("is_analysis >=", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisLessThan(int value) {
            addCriterion("is_analysis <", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisLessThanOrEqualTo(int value) {
            addCriterion("is_analysis <=", value, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisIn(List<Integer> values) {
            addCriterion("is_analysis in", values, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisNotIn(List<Integer> values) {
            addCriterion("is_analysis not in", values, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisBetween(int value1, int value2) {
            addCriterion("is_analysis between", value1, value2, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andIsAnalysisNotBetween(int value1, int value2) {
            addCriterion("is_analysis not between", value1, value2, "isAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeIsNull() {
            addCriterion("analysis_time is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeIsNotNull() {
            addCriterion("analysis_time is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeEqualTo(Date value) {
            addCriterion("analysis_time =", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeNotEqualTo(Date value) {
            addCriterion("analysis_time <>", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeGreaterThan(Date value) {
            addCriterion("analysis_time >", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("analysis_time >=", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeLessThan(Date value) {
            addCriterion("analysis_time <", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeLessThanOrEqualTo(Date value) {
            addCriterion("analysis_time <=", value, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeIn(List<Date> values) {
            addCriterion("analysis_time in", values, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeNotIn(List<Date> values) {
            addCriterion("analysis_time not in", values, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeBetween(Date value1, Date value2) {
            addCriterion("analysis_time between", value1, value2, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andAnalysisTimeNotBetween(Date value1, Date value2) {
            addCriterion("analysis_time not between", value1, value2, "analysisTime");
            return (Criteria) this;
        }

        public Criteria andInnCodeIsNull() {
            addCriterion("inn_code is null");
            return (Criteria) this;
        }

        public Criteria andInnCodeIsNotNull() {
            addCriterion("inn_code is not null");
            return (Criteria) this;
        }

        public Criteria andInnCodeEqualTo(String value) {
            addCriterion("inn_code =", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeNotEqualTo(String value) {
            addCriterion("inn_code <>", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeGreaterThan(String value) {
            addCriterion("inn_code >", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("inn_code >=", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeLessThan(String value) {
            addCriterion("inn_code <", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeLessThanOrEqualTo(String value) {
            addCriterion("inn_code <=", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeLike(String value) {
            addCriterion("inn_code like", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeNotLike(String value) {
            addCriterion("inn_code not like", value, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeIn(List<String> values) {
            addCriterion("inn_code in", values, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeNotIn(List<String> values) {
            addCriterion("inn_code not in", values, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeBetween(String value1, String value2) {
            addCriterion("inn_code between", value1, value2, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnCodeNotBetween(String value1, String value2) {
            addCriterion("inn_code not between", value1, value2, "innCode");
            return (Criteria) this;
        }

        public Criteria andInnNameIsNull() {
            addCriterion("inn_name is null");
            return (Criteria) this;
        }

        public Criteria andInnNameIsNotNull() {
            addCriterion("inn_name is not null");
            return (Criteria) this;
        }

        public Criteria andInnNameEqualTo(String value) {
            addCriterion("inn_name =", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameNotEqualTo(String value) {
            addCriterion("inn_name <>", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameGreaterThan(String value) {
            addCriterion("inn_name >", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameGreaterThanOrEqualTo(String value) {
            addCriterion("inn_name >=", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameLessThan(String value) {
            addCriterion("inn_name <", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameLessThanOrEqualTo(String value) {
            addCriterion("inn_name <=", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameLike(String value) {
            addCriterion("inn_name like", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameNotLike(String value) {
            addCriterion("inn_name not like", value, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameIn(List<String> values) {
            addCriterion("inn_name in", values, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameNotIn(List<String> values) {
            addCriterion("inn_name not in", values, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameBetween(String value1, String value2) {
            addCriterion("inn_name between", value1, value2, "innName");
            return (Criteria) this;
        }

        public Criteria andInnNameNotBetween(String value1, String value2) {
            addCriterion("inn_name not between", value1, value2, "innName");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(Date value) {
            addCriterion("create_time =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(Date value) {
            addCriterion("create_time >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(Date value) {
            addCriterion("create_time <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<Date> values) {
            addCriterion("create_time in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "modifier");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}