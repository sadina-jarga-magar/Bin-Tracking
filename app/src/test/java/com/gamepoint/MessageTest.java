package com.gamepoint;
import com.bintracking.model.ChatMessage;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;

public class MessageTest {
    @Test
    public void TestChat()
    {
        try {
                String message = "hello";
                Assert.assertThat(String.format("hello",message),
                        ChatMessage.checkmessage(message),
                        is(false));
        }
        catch
        (Exception e)
        {
            e.printStackTrace();
        }
    }
}
