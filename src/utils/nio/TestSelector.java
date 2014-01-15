package utils.nio;

import java.nio.channels.Selector;

public class TestSelector{
	private static final int MAXSIZE = 1;

	public static final void main(String argc[]){
		Selector[] sels = new Selector[MAXSIZE];
		try {
			for (int i = 0; i < MAXSIZE; ++i) {
				sels[i] = Selector.open();
				// sels[i].close();
			}
			Thread.sleep(1000 * 60 * 10);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
