module api {
    requires spark.core;
    requires login;
    requires login.email;
    requires login.qr;

    exports api;
}