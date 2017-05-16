package nkser.tmpayzee;

import android.app.Application;

/**
 * Created by Adam_Yang on 2017/5/14.
 */

public class GlobalValues extends Application {
	public final int mazeStart[][]={{9,1},{9,1},{4,1},{6,1}};
	public final int mazeDestination[][]={{1,10},{6,10},{2,10},{4,10}};

	public int[][][] maze;

	GlobalValues() {
		maze = new int[11][11][4];
	}

}
