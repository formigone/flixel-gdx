package org.flixel.data;

/**
 * Just a helper structure for the FlxSprite animation system
 */
public class FlxAnim
{
	/**
	 * String name of the animation (e.g. "walk")
	 */
	public String name;
	/**
	 * Seconds between frames (basically the framerate)
	 */
	public float delay;
	/**
	 * A list of frames stored as <code>uint</code> objects
	 */
	public int[] frames;
	/**
	 * Whether or not the animation is looped
	 */
	public boolean looped;

	/**
	 * Constructor
	 * 
	 * @param	Name		What this animation should be called (e.g. "run")
	 * @param	Frames		An array of numbers indicating what frames to play in what order (e.g. 1, 2, 3)
	 * @param	FrameRate	The speed in frames per second that the animation should play at (e.g. 40)
	 * @param	Looped		Whether or not the animation is looped or just plays once
	 */
	public FlxAnim(String Name, int[] Frames, float FrameRate, boolean Looped)
	{
		name = Name;
		delay = 0;
		if(FrameRate > 0)
			delay = 1.0f/FrameRate;
		frames = Frames;
		looped = Looped;
	}

}