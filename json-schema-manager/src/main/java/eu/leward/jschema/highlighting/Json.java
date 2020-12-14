package eu.leward.jschema.highlighting;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Json {

    private final JsonFactory jsonFactory;

    public Json() {
        jsonFactory = new JsonFactory();
    }

    public StyleSpans<Collection<String>> highlight(String code) {
        List<Match> matches = new ArrayList<>();

        try {
            JsonParser parser = jsonFactory.createParser(code);
            while (!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();
                int start = (int) parser.getTokenLocation().getCharOffset();
                int end = start + parser.getTextLength();

                // Because getTextLength() does contain the surrounding ""
                if(jsonToken == JsonToken.VALUE_STRING || jsonToken == JsonToken.FIELD_NAME) {
                    end += 2;
                }

                String className = jsonTokenToClassName(jsonToken);
                if (!className.isEmpty()) {
                    Match m = new Match(className, start, end);
                    matches.add(m);
                }
            }
        } catch (IOException e) {
            System.err.printf("JSON Error: %s%n", e.getMessage());
        }

        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastPos = 0;
        for (Match match : matches) {
            // Fill the gaps, since Style Spans need to be contiguous.
            if (match.start > lastPos) {
                int length = match.start - lastPos;
                spansBuilder.add(Collections.emptyList(), length);
            }

            int length = match.end - match.start;
            spansBuilder.add(Collections.singleton(match.kind), length);
            lastPos = match.end;
        }

        return spansBuilder.create();
    }

    public static String jsonTokenToClassName(JsonToken jsonToken) {
        if (jsonToken == null) {
            return "";
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return "json-property";
            case VALUE_STRING:
                return "json-string";
            case VALUE_NUMBER_FLOAT:
            case VALUE_NUMBER_INT:
                return "json-number";
            default:
                return "";
        }
    }

}
