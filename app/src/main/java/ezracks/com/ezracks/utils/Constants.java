package ezracks.com.ezracks.utils;

import android.content.Context;

/**
 * Created by aipxperts-ubuntu-01 on 30/3/17.
 */

public class Constants {

    public static Context _context;
    public static FieldsValidator mValidator;

    public Constants(Context context) {

        this._context = context;
        mValidator = new FieldsValidator(_context);
    }


    public static final String[] Login_param=
            {
                    "driverId",
                    "username",
                    "password",
                    "autologin",
                    "rememberme"
            };


    public static final String[] Store_detail_param=
            {
                    "authKey",
                    "store_name"
            };

    public static final String[] Change_password_param=
            {
                    "authKey",
                    "oldPassword",
                    "newPassword",
                    "confirmPassword"
            };

    public static final String[] Item_all_detail = {

            "driverId",
            "orderNumber",
            "descrip1",
            "descrip2",
            "descrip3",
            "descrip4",
            "descrip5",
            "descrip6",
            "descrip7",
            "descrip8",
            "name",
            "address",
            "city",
            "state",
            "zip",
            "orderdate",
            "storecode",
            "fl_store_to_be_reassigned",
            "authKey",
            "status"

    };
}
