package edu.usc.marshall.centralis22.service;

import edu.usc.marshall.centralis22.model.SimUser;
import edu.usc.marshall.centralis22.service.requesthandler.AbstractRequestHandler;
import edu.usc.marshall.centralis22.util.RequestResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

/**
 * Wrapper of {@link AbstractRequestHandler}. Sorts each request by type.
 */
@Service
public class RequestDispatcher {

    private final AbstractRequestHandler handler = new AbstractRequestHandler() {
        @Override
        public void handle(SimUser user, Object content, RequestResponseEntity rre) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter rre status code:");
            rre.setStatusCode(Integer.parseInt(sc.nextLine()));
            System.out.println("Enter rre content:");
            rre.setContent(sc.nextLine());
            System.out.println("Enter rre status message:");
            rre.setStatusMessage(sc.nextLine());
        }
    };

    /**
     * Calls the corresponding {@link AbstractRequestHandler} implementation based on
     * {@code request}. The data must be in compliance with the API. If not,
     * an exception will be thrown, failing the request.
     *
     * @param user
     * @param data JSON object provided by the frontend.
     * @param rre Return response to a request.
     */
    public void dispatch(SimUser user, Map<String, Object> data, RequestResponseEntity rre) {

        AbstractRequestHandler requestHandler;

        String request = (String)data.get("request");
        Object content = data.get("content");

        requestHandler = handler;
        requestHandler.handle(user, content, rre);
    }
}
