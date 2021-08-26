package bot.assembly.memory;

//  BotMemory imitates a bot's memory unit that stores the commonly used messages
//  and tracks the tasks added
public class BotStaticMemoryUnit {

    public final String LOGO =
                            "                                    ,wpp@@ppwa,\n" +
                            "                                ,p@R@@@@@@@@@MMM@p,\n" +
                            "                              a@@@MMMM%MDZ\"|||,p[]g,\n" +
                            "                            ,@K\"@@N;j**\"\"`\"uww- x   U,\n" +
                            "                           )TK*`\"\"* {; \"   [ +*\"**,zH;..`>\n" +
                            "                          ,TR|       Yq,,,4-       H    ;  V\n" +
                            "                          BKH       .w;     \"KWwwK\"`      ; U\n" +
                            "                         ]KR          ``*+<a,JJ,aw~o`      ;\"\n" +
                            "                         BKH                       ;;N       U\n" +
                            "                        jTK|                      .A-        {\n" +
                            "                        {KR                      -D;        |j\n" +
                            "                        [KR                        ;`p       J\n" +
                            "                        lKm            ]|            {       J\n" +
                            "                       z@KR            'M.|  ,AQm.|,aH       J\n" +
                            "                      Aj`%K             '*TT\"'|`\"''          J<\n" +
                            "                 ,~Q;KEMkK\"v-                               .C Y.\n" +
                            "             ,wCmKK*\"''\"KC  `*;                            y\" ,U  `>.\n" +
                            "          ,mTmKK'        'Y,   \"<;                       ,4  ,-       *.\n" +
                            "        wEmKK\"              \"y    '*E-                 )*   A           `>\n" +
                            "      mTKKKP                  `*u     \"*VL;         ,W`   z\"    [          V`y\n" +
                            "   ,Am**\"\"\"**<,                   \"~,      `\"*~v; yPw,  x`      1          { j*\n" +
                            " ,BjKK*\"''      `*,                  `\"~,    A   *H  `Y`         @         U A 1\n" +
                            " 2Ek6,gpwwwww,     \".                     '\"*    \"n               Mm     ,G    C\n" +
                            "[I$mTKKR\"|    -Ey    \\   /                         \"               \\K,,aP    ,CE\n" +
                            "`4KKKKR          |\\   [z`                           \"p              'B;Jw ,mE  H\n" +
                            "  mKKR            ;\" 1/                              \"p              '@R\"'    ]\n" +
                            "  {KKH              \\]                              \"*?pz-~            @m     H\n" +
                            "   @KH               U                                  y               M|   /\n" +
                            "   `@R              ;`                                   \\               H  j\n" +
                            "     VM               $                                   \\              J- H\n" +
                            "      \\m|              U                                 UUU              H;]\n" +
                            "       B%|      `      [                                   ]              E j\n" +
                            "    ,@R\"*KM   lR|      ]                                   J              H CX\n" +
                            "     %@wK&\"*@pE$m      [                                   J             ]`j ]\n" +
                            "       `BH iB| ]B`\"p   U                                 -,I         z  w;|,A\n" +
                            "        `@mKBpl$[  ( ,B.                                 `,U       ,(g@`\n" +
                            "         1@@M@@@@ppa@@@@pp@Kp.     V.                    z\"    ,aw@@@@M\n" +
                            "          %@@@@@M@@@@@@@@@@@@@@@@@ppE*@pp,,.     .,,pww%T%K@@@@@M@@M@@R\n" +
                            "           M@@@@@@@@@MMM@@@@@@@@@@M@@@@@@@@@@@@@@@KKKKKKKKj@@@MMM@@@MM\n" +
                            "            %@@@@@@@@@@@@@@@@@MMMMM@@MM@@@@@@@@@MMMppkpp@@@@@@@@@@@MR\n" +
                            "             \"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P\n" +
                            "               %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@R\n" +
                            "                \"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@M*\n" +
                            "                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                            "                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@M@@@@@@@@@@@@@@]\n" +
                            "                ]@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%\n" +
                            "                @@@@@@@@@@@@@@@@@@@@@@@@@@M@@@@@@@@@@@@@@@@@@R\n" +
                            "               ]@@@@@@@@@@@@@@@@@@@@@@@@@MM@@@@@@@@@@@@@@@@@@@U\n" +
                            "               $@@@@@@@@@@@@@@@@@@@@@@@@@MM@@@@@@@@@@@@@@@@@@@@\n" +
                            "               @@@@@@@@@@@@@@@@@@@@@@@@@@@M@@@@@@@@@@@@@@@@@@@R\n" +
                            "               @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@M@@@U\n" +
                            "               8@@@@@@@@@@@@@@@@@@@@@@@@@@$@@@@@@@@@@@@@@@@@@@@@@W\n" +
                            "               1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N\n" +
                            "                %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                            "                [@@@@@@@@@@@@@@@@@@@@@@@@@@@@@R@@@@@@@@@@@@@@@@@@@@@M\n" +
                            "                1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@MM*`\n" +
                            "                  `\"%MM@@@@@@@@@@@@@@@@@@@@@MM\"   `'\"````\"'-\n" +
                            "                         \"\"\"\"**PMMMMMP**\"`-";

    public final String MESSAGE_GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    public final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    public final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    public final String MESSAGE_TASK_COMPLETE = "Task Completed:";
    public final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    public final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    public final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in your list";
    public final String MESSAGE_REMOVE_TASK = "YEAH! You have removed one task from your list!";
    public final String ERROR_MESSAGE_PROMPT = "ERROR: ";
    public final String ERROR_MESSAGE_INVALID_COMMAND = "OPS! I am not even sure whether I can accept this command!";
    public final String ERROR_MESSAGE_INVALID_COMMAND_FORMAT = "OPS! Your command format is wrong! Enter in the right format please!";
    public final String ERROR_MESSAGE_EMPTY_TASKLIST = "HEY! You have no task at hand! Get your life together!";
    public final String ERROR_MESSAGE_TASK_OUT_OF_RANGE = "HEY! You don't have that many tasks!";
    public final String ERROR_MESSAGE_INVALID_TASK_INDEX = "HOLD ON! The index you entered is not an Integer!";
    public final String ERROR_MESSAGE_INVALID_DATA_FORMAT = "HOLD ON! The data: \n\t\t%s\n\t is in the wrong format! Stop feeding me shit!";
    public final String ERROR_MESSAGE_INVALID_FILE = "WAIT! There are some errors with your file!";
    public final String ERROR_MESSAGE_INVALID_DATETIME_FORMAT = "I CANNOT UNDER THE TIMING FORMAT! Please input in the following format \n\t\t{yyyy-mm-ddThh:mm:ss}";

    public BotStaticMemoryUnit(){}

}
