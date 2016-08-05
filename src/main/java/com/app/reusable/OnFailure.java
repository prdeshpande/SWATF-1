package com.app.reusable;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 08.09.14
 */
public class OnFailure extends TestListenerAdapter{



    @Step("Failure captured, documented with: ")
    @Override
    public void onTestFailure(ITestResult tr) {

        createAttachment(tr);


    }

    @Attachment("Hi, I'm attachment in your testng listener")
    private String createAttachment(ITestResult tr) {
        return tr.getName();
    }


}