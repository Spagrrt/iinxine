package engine;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get(){
        if (instance == null){
            instance = new MouseListener();
        }

        return instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos){
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xPos;
        get().yPos = yPos;
        get().isDragging = get().mouseButtonPressed[0];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if(button < get().mouseButtonPressed.length) {
            if (action == GLFW_PRESS) {
                get().mouseButtonPressed[button] = true;
            } else {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getXPos(){
        return (float)get().xPos;
    }

    public static float getYPos(){
        return (float)get().yPos;
    }

    public static float getDx(){
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy(){
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX(){
        return (float)get().scrollX;
    }

    public static float getScrollY(){
        return (float)get().scrollY;
    }

    public static boolean IsDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button){
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        }
        else{
            return false;
        }
    }
}
