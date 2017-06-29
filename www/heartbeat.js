var heartbeatExport = {};

heartbeatExport.start = function (data, successCallback, errorCallback) {

    if (data == null || typeof data !== 'object') {
        if (errorCallback) {
            errorCallback({
                code: "INVALID_INPUT",
                message: "Invalid Input"
            });
        }
        return;
    }

    var url = data.url;

    cordova.exec(successCallback, errorCallback, "Heartbeat", "start", [url]);
};

heartbeatExport.stop = function (data, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Heartbeat", "stop", []);
};

module.exports = heartbeatExport;
