/* eslint-disable */
!function (e) {
    var t, n, o, i, c,
        d = '<svg><symbol id="icon-xunhuan" viewBox="0 0 1066 1024"><path d="M661.333333 1002.666667c-16.384 0-32.768-6.229333-45.269333-18.730667l-85.333333-85.333333A63.957333 63.957333 0 0 1 576 789.333333h85.333333c152.917333 0 277.333333-124.416 277.333334-277.333333s-124.416-277.333333-277.333334-277.333333a64 64 0 1 1 0-128c223.488 0 405.333333 181.845333 405.333334 405.333333 0 203.562667-150.869333 372.565333-346.666667 401.066667a64 64 0 0 1-58.666667 89.6zM405.333333 917.333333C181.845333 917.333333 0 735.488 0 512c0-203.562667 150.869333-372.565333 346.666667-401.066667A64 64 0 0 1 450.602667 40.106667l85.333333 85.333333A63.957333 63.957333 0 0 1 490.666667 234.666667h-85.333334C252.416 234.666667 128 359.082667 128 512s124.416 277.333333 277.333333 277.333333a64 64 0 1 1 0 128z" fill="#333333" ></path></symbol><symbol id="icon-xunhuan1" viewBox="0 0 1142 1024"><path d="M898.481231 565.878154v158.601846h-32.768c-125.794462 0-196.411077-96.886154-255.015385-202.633846 58.604308-105.708308 129.260308-202.633846 255.015385-202.633846h32.728615v158.601846L1122.461538 248.753231 898.441846 19.692308v158.562461h-32.689231c-168.881231 0-270.572308 107.52-334.296615 204.406154-65.496615-100.430769-165.415385-204.406154-323.938462-204.406154H19.692308v140.996923h187.82523c115.436308 0 182.626462 88.064 246.390154 202.594462-58.604308 98.658462-127.527385 184.989538-246.390154 184.989538H19.692308v140.996923h187.82523c160.256 0 260.174769-95.153231 323.938462-188.573538 63.763692 98.697846 165.415385 206.178462 334.257231 206.178461h32.728615V1024l224.019692-229.060923-224.019692-229.060923z"  ></path></symbol></svg>',
        a = (a = document.getElementsByTagName("script"))[a.length - 1].getAttribute("data-injectcss"),
        r = function (e, t) {
            t.parentNode.insertBefore(e, t)
        };
    if (a && !e.__iconfont__svg__cssinject__) {
        e.__iconfont__svg__cssinject__ = !0;
        try {
            document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")
        } catch (e) {
            console && console.log(e)
        }
    }

    function l() {
        c || (c = !0, o())
    }

    function s() {
        try {
            i.documentElement.doScroll("left")
        } catch (e) {
            return void setTimeout(s, 50)
        }
        l()
    }

    t = function () {
        var e, t = document.createElement("div");
        t.innerHTML = d, d = null, (t = t.getElementsByTagName("svg")[0]) && (t.setAttribute("aria-hidden", "true"), t.style.position = "absolute", t.style.width = 0, t.style.height = 0, t.style.overflow = "hidden", t = t, (e = document.body).firstChild ? r(t, e.firstChild) : e.appendChild(t))
    }, document.addEventListener ? ~["complete", "loaded", "interactive"].indexOf(document.readyState) ? setTimeout(t, 0) : (n = function () {
        document.removeEventListener("DOMContentLoaded", n, !1), t()
    }, document.addEventListener("DOMContentLoaded", n, !1)) : document.attachEvent && (o = t, i = e.document, c = !1, s(), i.onreadystatechange = function () {
        "complete" == i.readyState && (i.onreadystatechange = null, l())
    })
}(window);