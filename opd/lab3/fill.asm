ORG 0x3BE

P: WORD 0x03D7 ; POINTER
C: WORD 0x0200 ; CURRENT
L: WORD 0x4000 ; LENGTH
R: WORD 0x0200 ; RESULT

START:  LD #0x80
        DEC
        SWAB
        ST R
        LD #0x05
        ST L
        LD P
        ST C
J1:     LD (C)+
        ROR
        BCS S1
        ROR
        BCS S1
        ROL
        ROL
        BGE S1
        ST R
S1:     LOOP 0x03C0
        JUMP J1
        HLT

ARR: WORD 0x0301, 0x0580, 0x0381, 0x23CF, 0xF200