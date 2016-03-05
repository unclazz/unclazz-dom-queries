package org.unclazz.dom1;

import org.w3c.dom.ProcessingInstruction;

final class DefaultProcessingInstructionNode extends AbstractTreeStructureLeaf implements ProcessingInstructionNode {
	private final ProcessingInstruction inner;
	DefaultProcessingInstructionNode(final ProcessingInstruction inner) {
		super(inner);
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return "ProcessingInstructionNode()";
	}

	@Override
	public ProcessingInstruction getWrappedNode() {
		return inner;
	}
}
