;; Run a live script
;;  boot -s "src/" -f live/allnix/learn/brave_ch6_live.clj
;;
;; Start REPL with source paths
;;  boot -s "live/" -s "src/" repl

(set-env! :dependencies '[[boot/core "2.8.3"]
                          [boot/base "2.8.3"]
                          [org.clojure/clojure "1.10.1"]
                          [rm-hull/infix "0.3.3"]
                          ]
          :source-paths #{"src/" }
          )
