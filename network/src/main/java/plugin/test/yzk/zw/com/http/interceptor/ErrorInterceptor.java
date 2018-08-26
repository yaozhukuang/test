package plugin.test.yzk.zw.com.http.interceptor;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import plugin.test.yzk.zw.com.http.exception.CustomException;


/**
 * 抛出网络错误
 *
 * @author 0220000957
 */

public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (!response.isSuccessful()) {
                int errorCode = response.code();
                throw new CustomException(errorCode);
            }
            return response;
        } catch (UnknownHostException e) {
            throw new CustomException(CustomException.UNKNOWN_HOST);
        } catch (SocketException e) {
            throw new CustomException(CustomException.SOCKET_ERROR);
        } catch (SocketTimeoutException e) {
            throw new CustomException(CustomException.SOCKET_TIMEOUT);
        } catch (IOException e) {
            throw new CustomException(CustomException.IO_ERROR);
        }
    }
}
