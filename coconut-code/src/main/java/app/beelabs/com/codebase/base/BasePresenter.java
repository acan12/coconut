package app.beelabs.com.codebase.base;

import retrofit2.Response;

public class BasePresenter  implements IDao {
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

    public void call() {
    }

    @Override
    public void responseHandler(IPresenter presenter, int responseApiCode, Response response) {

    }
}
