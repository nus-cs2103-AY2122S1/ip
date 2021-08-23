package bloom.exception.command;

import bloom.exception.BloomException;

public class BloomUnknownCommandException extends BloomException {
	public BloomUnknownCommandException(String errorMessage) {
		super(errorMessage);
	}
}
