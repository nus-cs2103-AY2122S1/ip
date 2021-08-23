package bloom.constant;

public enum Drawing {
	
	HORIZONTAL_LINE("* * * * * * * * * * * * * * * * * * * *"), 
	LOGO(
			"\t   ____  _                       \n" + 
			"\t  |  _ \\| |                      \n" +
			"\t  | |_) | | ___   ___  _ __ ___  \n" +
			"\t  |  _ <| |/ _ \\ / _ \\| '_ ` _ \\ \n" +
			"\t  | |_) | | (_) | (_) | | | | | |\n" +
			"\t  |____/|_|\\___/ \\___/|_| |_| |_|\n");
	
	private final String drawing;
	
	Drawing(String drawing) {
		this.drawing = drawing;
	}
	
	public String getDrawing() {
		return this.drawing;
	}
}
