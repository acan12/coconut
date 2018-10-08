package app.beelabs.com.codebase.base;

public class BasePresenter {
    private static BasePresenter presenter;


    public static BasePresenter getInstance(Class clazz) {
        try {
            if (presenter == null) {
                presenter = (BasePresenter) clazz.newInstance();

                return presenter;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
