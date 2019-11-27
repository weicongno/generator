package com.weicongno.generator.commons.parsing;


import org.apache.commons.lang.StringUtils;

/**
 * 通用的token解析器
 * @version 1.0 createTime:2019/9/17 16:55
 * @author:weicong
 */
public class GenericTokenParser {
    private final String openToken;
    private final String closeToken;
    private final TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    /**
     * 解析
     * @param text
     * @return
     */
    public String parse(String text) {
        if (text != null && !text.isEmpty()) {
            int start = text.indexOf(this.openToken);
            if (start == -1) {
                return text;
            } else {
                char[] src = text.toCharArray();
                int offset = 0;
                StringBuilder builder = new StringBuilder();

                for(StringBuilder expression = null; start > -1; start = text.indexOf(this.openToken, offset)) {
                    if (start > 0 && src[start - 1] == '\\') {
                        builder.append(src, offset, start - offset - 1).append(this.openToken);
                        offset = start + this.openToken.length();
                    } else {
                        if (expression == null) {
                            expression = new StringBuilder();
                        } else {
                            expression.setLength(0);
                        }

                        builder.append(src, offset, start - offset);
                        offset = start + this.openToken.length();

                        int end;
                        for(end = text.indexOf(this.closeToken, offset); end > -1; end = text.indexOf(this.closeToken, offset)) {
                            if (end <= offset || src[end - 1] != '\\') {
                                expression.append(src, offset, end - offset);
                                int var10000 = end + this.closeToken.length();
                                break;
                            }

                            expression.append(src, offset, end - offset - 1).append(this.closeToken);
                            offset = end + this.closeToken.length();
                        }

                        if (end == -1) {
                            builder.append(src, start, src.length - start);
                            offset = src.length;
                        } else {
                            builder.append(this.handler.handleToken(expression.toString()));
                            offset = end + this.closeToken.length();
                        }
                    }
                }

                if (offset < src.length) {
                    builder.append(src, offset, src.length - offset);
                }

                return builder.toString();
            }
        } else {
            return "";
        }
    }

    public static void main(String[] args){
        System.out.println(StringUtils.replace("123a$", "a$", "?"));
    }
}
