%Author: Michael Austin
% Academic Integrity Statement: I have complied with the WVU Academic
% Integrity policy and the submissions I make are solely my own. Etc.
% Etc.
lg2(0,0).
lg2(1,0).
lg2(N,Iterations) :-
    N > 1,
    Nsub is N // 2,
    lg2(Nsub, Iters1),
    Iterations is Iters1 + 1.

lgstar(N,0) :- N =< 1.
lgstar(N,Iterations) :-
    N > 1,
    lg2(N,Nlg),
    lgstar(Nlg,Iters2),
    Iterations is 1 + Iters2.
