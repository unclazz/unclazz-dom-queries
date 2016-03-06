package org.unclazz.dom.queries;

import org.w3c.dom.ProcessingInstruction;

final class DefaultProcessingInstructionNode extends AbstractTreeStructureLeaf implements ProcessingInstructionNode {
	private final ProcessingInstruction inner;
	DefaultProcessingInstructionNode(final ProcessingInstruction inner) {
		super(inner);
		this.inner = inner;
	}

	@Override
	public ProcessingInstruction getWrappedNode() {
		return inner;
	}
}
