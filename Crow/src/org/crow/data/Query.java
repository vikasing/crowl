/**
 * 
 */
package org.crow.data;

/**
 * @author viksin
 *
 */
public class Query {
	
	private String type;
	private String statement;
	private String[] selectColumns;
	private String[] updateColumns;
	private String selectColumn;
	private String updateColumn;
	private String whereClause;
	
	private String mongoDB;
	private String mongoCollection;
	/**
	 * @param type: set type as "select","update","delete","insert"
	 */
	public void setType(String queryType) {
		this.type = queryType;
	}
	/**
	 * @return the queryType
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param queryStatement the queryStatement to set
	 */
	public void setStatement(String queryStatement) {
		this.statement = queryStatement;
	}
	/**
	 * @return the queryStatement
	 */
	public String getStatement() {
		return statement;
	}
	/**
	 * @param selectColumns the selectColumns to set
	 */
	public void setSelectColumns(String[] selectColumns) {
		this.selectColumns = selectColumns;
	}
	/**
	 * @return the selectColumns
	 */
	public String[] getSelectColumns() {
		return selectColumns;
	}
	/**
	 * @param whereClause the whereClause to set
	 */
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	/**
	 * @return the whereClause
	 */
	public String getWhereClause() {
		return whereClause;
	}
	/**
	 * @param updateColumns the updateColumns to set
	 */
	public void setUpdateColumns(String[] updateColumns) {
		this.updateColumns = updateColumns;
	}
	/**
	 * @return the updateColumns
	 */
	public String[] getUpdateColumns() {
		return updateColumns;
	}
	/**
	 * @param updateColumn the updateColumn to set
	 */
	public void setUpdateColumn(String updateColumn) {
		this.updateColumn = updateColumn;
	}
	/**
	 * @return the updateColumn
	 */
	public String getUpdateColumn() {
		return updateColumn;
	}
	/**
	 * @param selectColumn the selectColumn to set
	 */
	public void setSelectColumn(String selectColumn) {
		this.selectColumn = selectColumn;
	}
	/**
	 * @return the selectColumn
	 */
	public String getSelectColumn() {
		return selectColumn;
	}
	/**
	 * @param mongoDB the mongoDB to set
	 */
	public void setMongoDB(String mongoDB) {
		this.mongoDB = mongoDB;
	}
	/**
	 * @return the mongoDB
	 */
	public String getMongoDB() {
		return mongoDB;
	}
	/**
	 * @param mongoCollection the mongoCollection to set
	 */
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
	}
	/**
	 * @return the mongoCollection
	 */
	public String getMongoCollection() {
		return mongoCollection;
	}
}
