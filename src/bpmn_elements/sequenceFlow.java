package bpmn_elements;

public class sequenceFlow extends Item {
	private String sourceref;
	private String targetref;

// source reference
	public String getsourceref() {
		return sourceref;
	}

	public void setSourceref(String sourceref) {
		this.sourceref = sourceref;
	}

// target reference
	public String gettargetref() {
		return targetref;
	}

	public void setTargetref(String targetref) {
		this.targetref = targetref;
	}
	
	@Override
	public String toString() {
		return "[SEQ source=" + sourceref + ", target=" + targetref + "]";
	}
}
