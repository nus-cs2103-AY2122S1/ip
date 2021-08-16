public class Duke {

    private static void intro() {
        String welcomeText = "                                                                                                            \n" +
                "I8,        8        ,8I  88                                                 88                              \n" +
                "`8b       d8b       d8'  88                                          ,d     88                              \n" +
                " \"8,     ,8\"8,     ,8\"   88                                          88     88                              \n" +
                "  Y8     8P Y8     8P    88,dPPYba,    ,adPPYba,  8b,dPPYba,       MM88MMM  88,dPPYba,    ,adPPYba,         \n" +
                "  `8b   d8' `8b   d8'    88P'    \"8a  a8P_____88  88P'   `\"8a        88     88P'    \"8a  a8P_____88         \n" +
                "   `8a a8'   `8a a8'     88       88  8PP\"\"\"\"\"\"\"  88       88        88     88       88  8PP\"\"\"\"\"\"\"         \n" +
                "    `8a8'     `8a8'      88       88  \"8b,   ,aa  88       88        88,    88       88  \"8b,   ,aa         \n" +
                "     `8'       `8'       88       88   `\"Ybbd8\"'  88       88        \"Y888  88       88   `\"Ybbd8\"'         \n" +
                "                                                                                                            \n" +
                "                                                                                                            \n" +
                "                                                                                                            \n" +
                "88888888ba,                 88                        88                                                    \n" +
                "88      `\"8b                88                        \"\"                                                    \n" +
                "88        `8b               88                                                                              \n" +
                "88         88  88       88  88   ,d8   ,adPPYba,      88  ,adPPYba,      ,adPPYba,  88       88  ,adPPYba,  \n" +
                "88         88  88       88  88 ,a8\"   a8P_____88      88  I8[    \"\"      I8[    \"\"  88       88  I8[    \"\"  \n" +
                "88         8P  88       88  8888[     8PP\"\"\"\"\"\"\"      88   `\"Y8ba,        `\"Y8ba,   88       88   `\"Y8ba,   \n" +
                "88      .a8P   \"8a,   ,a88  88`\"Yba,  \"8b,   ,aa      88  aa    ]8I      aa    ]8I  \"8a,   ,a88  aa    ]8I  \n" +
                "88888888Y\"'     `\"YbbdP'Y8  88   `Y8a  `\"Ybbd8\"'      88  `\"YbbdP\"'      `\"YbbdP\"'   `\"YbbdP'Y8  `\"YbbdP\"'  \n" +
                "                                                                                                            \n" +
                "                                                                                                            ";

        String mascot ="\n" +
                "       `:ossys/`              \n" +
                "     .yh+-` ``-o/             \n" +
                "     hh         .o.           \n" +
                "     Ns  -+sssyyo:y:          \n" +
                "    `Ms.o/-/+ossym+do         \n" +
                "    -M+s+::/+osssdy.dh.       \n" +
                "    +M:dsoooossshm- `dm-      \n" +
                "    dM./ddhyyhhdy-   `dN-     \n" +
                "   .Nh  `-/+o+:.      .mh     \n" +
                "   +M:                 -No    \n" +
                "   mm                   +M:   \n" +
                "  -Mo                    dd`  \n" +
                "  sM.                    -M+  \n" +
                " `mh                      hm  \n" +
                " :M/                      :M- \n" +
                " oM`                      `Mo \n" +
                " hh                        Ny \n" +
                "`No       ./syyys+-`       Nh \n" +
                "-M:    `/hmy/:--:+dh:      Nh \n" +
                ":M-  `ody:`        :my`   .Ms \n" +
                "-My/ydo.            `ym:  oM- \n" +
                " :++-                 /mddd: ";

        System.out.println(welcomeText + '\n' +
                mascot + '\n'
                + "What can I do for you?");
    }

    private static void listen() {
        return;
    }

    public static void main(String[] args) {
        intro();
        while(true) {
            listen();
        }
    }
}
