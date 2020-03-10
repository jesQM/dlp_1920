package ast.type.subclasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.type.AbstractType;
import visitor.Visitor;

public class RecordType extends AbstractType{

	private List<RecordField> fields;

	public RecordType(int line, int column, List<RecordField> fields) {
		super(line, column);
		this.fields = new ArrayList<>(fields);

		this.checkForDuplicateFieldName();
	}

	public List<RecordField> getFields() {
		return new ArrayList<>(this.fields);
	}

	@Override
	public String toString() {
		return "record {"+fields+"}";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> v, TP param) {
		return v.visit(this, param);
	}

	private void checkForDuplicateFieldName(){
		Set<String> usedNames = new HashSet<>();

		for (RecordField field: fields) {
			if ( !usedNames.add(field.getName()) ) {
				// If cannot add => already in set => create new error
				String message = String.format("Duplicate field name \'%s\'", field.getName());
				new ErrorType(getLine(), getColumn(), message);
			}
		}
	}
}
